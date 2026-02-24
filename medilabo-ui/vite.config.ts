import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5183, // Vite dev server port 
    proxy: {
      '/patient': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/notes': {
        target: 'http://localhost:8081',
        changeOrigin: true 
      },
      '/risk-assessment': {
        target: 'http://localhost:8082',
        changeOrigin: true 
      }
    }
  }
})
