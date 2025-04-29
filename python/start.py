import sys
import os
from transformers import SegformerImageProcessor, AutoModelForSemanticSegmentation
from PIL import Image
import torch.nn as nn
import numpy as np
from categories import categories
import io
import base64

if __name__ == "__main__":
    # получение пути к изображению
    image_path = sys.argv[1]
    # Получение пути к проекту
    script_dir = os.path.dirname(os.path.abspath(__file__))
    # Изменение текущей рабочей директории на директорию скрипта
    os.chdir(script_dir)
    # Загрузка модели и процессора
    processor = SegformerImageProcessor.from_pretrained("resources/model")
    model = AutoModelForSemanticSegmentation.from_pretrained("resources/model")
    # Получение словаря id2label
    id2label = model.config.id2label
    # Открытие и преобразование изображения RGB
    image = Image.open(image_path)
    image_rgb = image.convert('RGB')
    # Преобразование изображения и получение предсказаний модели
    inputs = processor(images=image_rgb, return_tensors="pt")
    outputs = model(**inputs)
    logits = outputs.logits.cpu()
    # Интерполяция логитов к размеру исходного изображения
    upsampled_logits = nn.functional.interpolate(logits, size=image.size[::-1], mode="bilinear", align_corners=False)
    pred_seg = upsampled_logits.argmax(dim=1)[0]
    # Получение уникальных классов в предсказании
    unique_classes = np.unique(pred_seg)
    # Для каждого уникального класса
    for unique_class in unique_classes:
        # Если этот класс нас не интересует, пропустим его
        if id2label[unique_class] in categories:
            # Создайте маску для этого класса
            mask = np.where(pred_seg == unique_class, 1, 0)
            # Получите координаты ненулевых пикселей
            non_zero_pixels = np.argwhere(mask)
            # Увеличение границ на 10 пикселей
            min_x = max(np.min(non_zero_pixels[:, 1]) - 10, 0)
            min_y = max(np.min(non_zero_pixels[:, 0]) - 10, 0)
            max_x = min(np.max(non_zero_pixels[:, 1]) + 10, image.size[0])
            max_y = min(np.max(non_zero_pixels[:, 0]) + 10, image.size[1])
            # Обрежьте изображение по этим координатам
            final_image_cropped = image.crop((min_x, min_y, max_x, max_y))
            # Сериализация изображения в байты
            byte_arr = io.BytesIO()
            final_image_cropped.save(byte_arr, format='PNG')
            byte_arr = byte_arr.getvalue()
            # Преобразование байтов в строку base64 и вывод в консоль
            base64_str = base64.b64encode(byte_arr).decode('utf-8')
            print(base64_str)
