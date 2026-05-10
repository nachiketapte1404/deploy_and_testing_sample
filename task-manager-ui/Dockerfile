# Stage 1: Build
FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration production --ssr false

# Stage 2: Serve with Nginx
FROM nginx:alpine
# Copy the build output to Nginx's html folder
# Note: Check your 'dist' folder name; it might be 'dist/task-manager-ui/browser'
COPY --from=build /app/dist/task-manager-ui/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80