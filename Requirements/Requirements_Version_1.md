# **Version 1**

## **UI & User Flow Design**
### **T1E-1: High-Fidelity UI Design**
- **T1S-1: Create High-Fidelity Mockups for User Flows**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Non-Functional  
  - **Story Description:** Create detailed, high-fidelity mockups for the user registration/login screen, inventory page, shopping cart, checkout process, and receipt screens.

---

## **User Authentication & Account Management**
### **T1E-2: User Authentication & Admin Role Management**
- **T1S-2: Implement User Self-Registration and Login**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 2-3 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement user registration, login functionality, and validation for creating accounts with unique usernames and 6-character passwords.
- **T1S-3: Implement Admin Account Creation and User Transformation**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Enable an admin to create new admin users and transform existing users into admins.

---

## **Inventory Management & Shopping Cart**
### **T1E-3: Inventory Management and User Interface**
- **T1S-4: Display Inventory List Sorted by Price**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement inventory display sorted from highest to lowest price, including item name, price, description, and at least one picture.
- **T1S-5: Format Item Prices in US Dollar Style**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Format item prices in US dollars, including a dollar sign ($), commas for thousands, and two decimal places (e.g., $1,234.56).
- **T1S-6: Display Item Prices in Base 10 Format**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Ensure that item prices are displayed in base 10 format (e.g., no floating-point values) to avoid rounding issues.
- **T1S-7: Implement Search Functionality for Inventory**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement search functionality that matches user input to item names or descriptions.
- **T1S-8: Add Items to Cart and Display Subtotal**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Allow users to add items to the cart, display the contents, and calculate the subtotal.
- **T1S-9: Remove Items from Cart and Handle Empty Cart**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Allow users to remove items from the cart and automatically return to the main inventory page when the cart is empty.
- **T1S-10: Filter Out Sold Items from Inventory**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement functionality that excludes items marked as sold from the inventory list so that users only see available products.

---

## **Checkout Process & Payment**
### **T1E-4: Cart and Checkout Functionality**
- **T1S-11: Implement Checkout Button and Prevent Empty Cart Checkout**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Ensure that the checkout button is only active when there are items in the cart and navigate to the checkout screen.
- **T1S-12: Display Selected Items Before Checkout**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** After clicking checkout, users must see a list of the items they have selected, including quantity, price, and subtotal in US dollars, before finalizing the purchase.
- **T1S-13: Allow Users to Return to Inventory from Shopping Cart**  
  - **Priority:** Needs to Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Provide an option for users to return to the inventory page from the shopping cart to continue shopping without losing their current cart contents.
- **T1S-14: Allow Users to Remove Items from Checkout List**  
  - **Priority:** Needs to Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** From the checkout review page, allow users to remove items from their selection list, updating the subtotal accordingly.

---

### **T1E-5: Payment and Confirmation**
- **T1S-15: Require "Pay Now" Button to Start Payment**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** From the checkout review page, users must click a "Pay Now" button to initiate the payment process and proceed to the payment form.
- **T1S-16: Collect User Payment Information and Shipping Details**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 2-3 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement fields to collect shipping address, credit card info (requires expiration month year, as well as CVV), and shipping speed selection from the user during checkout. All fields must be complete to confirm.
- **T1S-17: Display Final Cost and Confirmation**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 2-3 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Show a list of the items the user is buying (name and price only), subtotal, tax (6%), shipping speed cost, and grand total on the Confirm Order page, and confirm the order before processing.
- **T1S-18: Generate and Display Order Receipt**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1-2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Generate a receipt upon completing the order and display it with purchase details, shipping info, and last 4 digits of the credit card.
- **T1S-19: Show and Display Confirmation Email**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Non-Functional  
  - **Story Description:** Mock up the email receipt to be displayed in the browser upon completing the order.

---

## **Admin Features**
### **T1E-6: Admin Reporting and User Management**
- **T1S-20: Admin Sales Report and Export Functionality**  
  - **Priority:** Must Have  
  - **Estimated Effort:** 2 days  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Implement an admin sales report that shows all purchases and users. Allow exporting the report to a CSV file.
- **T1S-21: Allow Users to Return to Main Page from Receipt Page**  
  - **Priority:** Needs to Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Provide users with an option to return to the main page from the receipt page after completing an order.
- **T1S-22: Allow Users to Return to Checkout Page from Receipt Page** *(Ask Professor)*  
  - **Priority:** Needs to Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Allow users to navigate back to the checkout page from the receipt page. (Clarify with the professor if needed)

---

## **Cart Persistence and Navigation**
### **T1E-7: Cart Persistence and User Navigation**
- **T1S-23: Retain Cart Items When Returning to Main Page Without Completing Order**  
  - **Priority:** Needs to Have  
  - **Estimated Effort:** 1 day  
  - **Functional or Non-Functional:** Functional  
  - **Story Description:** Ensure that users can return to the main page without completing the order and retain their cart contents.
