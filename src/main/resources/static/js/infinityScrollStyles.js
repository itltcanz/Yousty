let page = 1;
const observer = new IntersectionObserver(async (entries) => {
    if (entries[0].isIntersecting) {
        const response = await fetch(`/api/styles?page=${page}`);
        const newStyles = await response.json();
        newStyles.forEach((style) => {
            // Создайте новый элемент для каждого стиля и добавьте его в список
            const styleCard = document.createElement('a');
            styleCard.className = 'style-card increasing block';
            styleCard.style.background = style.backColor;
            styleCard.style.color = style.textColor;
            styleCard.href = 'styles/' + style.name;

            const styleText = document.createElement('div');
            styleText.className = 'style-text';

            const h2 = document.createElement('h2');
            h2.textContent = style.name;

            const p = document.createElement('p');
            p.textContent = style.text;

            styleText.append(h2, p);

            let img;
            style.outfits.forEach((outfit) => {
                img = document.createElement('img');
                img.src = outfit.image.path;
                img.alt = 'Image';
                img.className = 'style-image';

                styleCard.append(img);
            });

            styleCard.append(styleText, img);

            // Добавьте новый элемент стиля в main
            document.querySelector('main').append(styleCard);
        });
        page++;
    }
}, { threshold: 0.5 });

// Начните наблюдение за элементом footer
observer.observe(document.querySelector('#infinite-scroll-marker'));
