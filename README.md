# My Springboot Ecom Project
### Project Overview
This Spring Boot application provides a comprehensive API for managing products and categories with features such as pagination, sorting, image uploads, and exception handling. The project is built with a modular structure, supporting CRUD operations for products and categories, and includes custom exception handling, model mapping, and configuration for scalability.

### Features
# Category Management:
Create, update, delete, and retrieve categories.
Supports pagination, sorting, and customizable page size and order.
# Product Management:
Add, update, delete, and retrieve products.
Search products by category or keyword.
Image upload and management for product images.
Calculate special pricing based on discount rates.
# Error Handling:
Custom exceptions for handling common errors like resource not found or invalid data.
Global exception handler for consistent error responses.
# Model Mapping:
Leverages ModelMapper for mapping between entities and DTOs, streamlining data handling.
### API Endpoints
# Category Endpoints
GET /api/public/categories - Retrieve all categories with pagination and sorting.
POST /api/public/addCategory - Add a new category.
DELETE /api/admin/category/{id} - Delete a category by ID.
PUT /api/public/category/{id} - Update a category by ID.
# Product Endpoints
GET /api/public/products - Retrieve all products with pagination and sorting.
POST /api/admin/categories/{category_id}/product - Add a product to a specific category.
GET /api/public/categories/{categoryId}/products - Get products by category.
GET /api/public/categories/search/{keyword}/products - Search products by keyword.
PUT /api/admin/products/{product_id} - Update product details by ID.
PUT /api/product/{productId}/image - Update product image.
DELETE /api/admin/products/deleteProducts/{productId} - Delete a product by ID.
### Error Handling
The application uses a global exception handler to manage exceptions, providing clear, consistent error messages across all API responses.
### Technologies Used
# Spring Boot: Core framework for the application.
# Spring Data JPA: Simplifies data access layer.
# Lombok: Reduces boilerplate code for models and DTOs.
# ModelMapper: Manages entity-to-DTO conversion.
# AWS (Optional): Placeholder for potential integration.
# Jakarta Validation: Enforces data constraints on input.
# Spring Security 6: Planned for future integration.
### Project Structure
├── Models/              # Contains entity classes for Product and Category
├── Payload/             # Data transfer objects and responses
├── Controllers/         # API endpoints for managing products and categories
├── Service/             # Business logic for the application
├── Repositories/        # Database access layer
├── AppConfig/           # Configuration classes, including ModelMapper and constants
└── MyGlobalException/   # Custom exceptions and global error handling
### Future Enhancements
# Security:
Integrate Spring Security 6 to secure API endpoints, manage roles and permissions, and protect sensitive data.

