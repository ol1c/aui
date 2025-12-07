# Angular Frontend Application

This is a complete Angular application that communicates with the existing microservices gateway to manage categories and items. The application follows modern Angular practices with routing and component-based architecture.

## Features

### Categories Management
- **Categories List** (`/categories`) - View all categories with add, edit, delete, and view details actions
- **Add Category** (`/categories/add`) - Create a new category
- **Edit Category** (`/categories/:id/edit`) - Edit an existing category
- **Category Details** (`/categories/:id`) - View category details and manage its items

### Items Management
- **Add Item** (`/categories/:categoryId/items/add`) - Create a new item in a category
- **Edit Item** (`/categories/:categoryId/items/:itemId/edit`) - Edit an existing item
- **Item Details** (`/categories/:categoryId/items/:itemId`) - View item details

## Project Structure

```
src/app/
├── categories/
│   ├── category-list/      # List all categories
│   ├── category-add/       # Add new category
│   ├── category-edit/      # Edit category
│   ├── category-details/   # View category with items
│   └── category.service.ts # Category CRUD operations
├── items/
│   ├── item-add/           # Add new item
│   ├── item-edit/          # Edit item
│   ├── item-details/       # View item details
│   └── item.service.ts     # Item CRUD operations
├── models/
│   ├── category.ts         # Category interface
│   └── item.ts             # Item interface
└── app.routes.ts           # Route definitions
```

## Prerequisites

- Node.js (v18 or higher)
- npm (v11.6.2 or higher)
- Angular CLI (v21.0.2)

## Installation

1. Install dependencies:
```bash
npm install
```

## Development

### Development Server

To start a local development server with proxy to the gateway:

```bash
npm start
```

The application will be available at `http://localhost:4200/`. The dev server uses a proxy configuration to forward API requests to the gateway at `http://localhost:8080`.

### Building

To build the project for production:

```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## API Configuration

The application is configured to communicate with the gateway API at:
- **Development**: `/api` (proxied to `http://localhost:8080`)
- **Production**: Configure in `src/environments/environment.prod.ts`

### API Endpoints Used

**Categories:**
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create new category
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

**Items:**
- `GET /api/items` - Get all items
- `GET /api/items?categoryId={id}` - Get items by category
- `GET /api/items/{id}` - Get item by ID
- `POST /api/items` - Create new item
- `PUT /api/items/{id}` - Update item
- `DELETE /api/items/{id}` - Delete item

## Running with Microservices

1. Start the gateway service (on port 8080)
2. Start the category service (on port 8082)
3. Start the item service (on port 8081)
4. Start the Angular application:
```bash
npm start
```

## Testing

To execute unit tests:

```bash
npm test
```

## Technology Stack

- **Angular 21** - Frontend framework
- **TypeScript 5.9** - Programming language
- **RxJS 7.8** - Reactive programming
- **Angular Router** - Navigation
- **Angular Reactive Forms** - Form handling
- **SCSS** - Styling

## Additional Resources

For more information on using the Angular CLI, visit the [Angular CLI Documentation](https://angular.dev/tools/cli).
