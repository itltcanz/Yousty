let page = 1;
const userId = document.querySelector('.account-info h2').getAttribute('user-id');
const observer = new IntersectionObserver(async (entries) => {
    if (entries[0].isIntersecting) {
        const response = await fetch(`/api/account/${userId}?page=${page}`);
        const newOutfits = await response.json();
        const outfitsContainer = document.querySelector('.outfits-container'); // Получите ссылку на элемент outfits-container
        newOutfits.forEach((outfit) => {
            // Создайте новый элемент для каждого наряда
            const outfitCard = document.createElement('a');
            outfitCard.className = 'outfit-card increasing';
            outfitCard.href = '/styles/' + outfit.style.id + '/' + outfit.id;

            const infoBlock = document.createElement('div');
            infoBlock.className = 'info block';

            const outfitImage = document.createElement('img');
            outfitImage.src = outfit.image.path;
            outfitImage.className = 'outfit';
            outfitImage.alt = 'Image';

            const creator = document.createElement('p');
            creator.textContent = outfit.creator.username;

            const like = document.createElement('img');
            like.src = '../images/static/like.png';
            like.className = 'like increasing';

            outfitCard.append(outfitImage);
            outfitCard.append(infoBlock);

            infoBlock.append(creator);
            infoBlock.append(like);

            outfitsContainer.append(outfitCard);
        });
        page++;
    }
}, { threshold: 1.0 });

// Начните наблюдение за элементом #infinite-scroll-marker
observer.observe(document.querySelector('#infinite-scroll-marker'));