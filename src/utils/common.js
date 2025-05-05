export const scrollTo = (id) => {
    const aboutSection = document.querySelector(id);
    if (aboutSection) {
        const yOffset = -80;
        const y = aboutSection.getBoundingClientRect().top + window.scrollY + yOffset;

        window.scrollTo({
            top: y,
            behavior: 'smooth'
        });
    }
};

export const scrollToTop = () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    })
}