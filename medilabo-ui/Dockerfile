# Stage 1: build
FROM node:20-alpine AS build

WORKDIR /app

# Copy package.json & package-lock.json / pnpm-lock.yaml / yarn.lock
COPY package*.json ./
# COPY pnpm-lock.yaml ./
# COPY yarn.lock ./

# Install dependencies
RUN npm install

# Copy all source code
COPY . .

# Copy env file
COPY .env.docker .env

# Build the app
RUN npm run build

# Stage 2: serve
FROM nginx:alpine

# Copy build output to nginx html folder
COPY --from=build /app/dist /usr/share/nginx/html

# Copy custom nginx config if needed
# COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]