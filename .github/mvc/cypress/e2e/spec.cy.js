describe('Formulario de contacto', () => {
  it('al enviar datos correctos muestra feedback positivo', () => {
    // Given ...
    cy.visit('http://localhost:8080/contacto');
    // When ...
    cy.get('#email').type('usuario@email.com');
    cy.get('#message').type('Probando Cypress.io');
    cy.get('input[value="Enviar"]').click();
    // Then ...
    cy.contains('p', 'Gracias usuario@email.com, tu mensaje ha sido recibido.');
  });
});
