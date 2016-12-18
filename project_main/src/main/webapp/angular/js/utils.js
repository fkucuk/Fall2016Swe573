function getFormattedDate() {
    return (currentDate.getFullYear() + ('0' + (currentDate.getMonth() + 1)).slice(-
        2) + ('0' + currentDate.getDate()).slice(-2));
};

