
document.querySelectorAll('.add-to-borrow-list').forEach(button => {
	button.addEventListener('click', function() {
		const bookId = this.getAttribute('data-book-id');
		const bookName = this.getAttribute('data-book-name');
		const bookCategory = this.getAttribute('data-book-category');
		const bookAuthor = this.getAttribute('data-book-author');

		console.log('Book clicked:', bookName, bookAuthor);

		const row = document.createElement('tr');
		row.innerHTML = `
	            <td>
				${bookId}
				<input name="bookId[]" type="text" value="${bookId}" class="d-none">
				</td>
	            <td>${bookName}</td>
	            <td>${bookCategory}</td>
	            <td>${bookAuthor}</td>
	            <td><button class="btn btn-danger btn-sm remove-book" data-book-id="${bookId}"><i class="bi bi-x-lg"></i></button></td>
	        `;

		document.getElementById('borrowedBooksBody').appendChild(row);

		row.querySelector('.remove-book').addEventListener('click', function() {
			this.closest('tr').remove();
		});
	});
});

