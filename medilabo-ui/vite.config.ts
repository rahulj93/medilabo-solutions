import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // This allows the host machine to access the containerized Vite dev server.
    port: 5183, // Vite dev server port 
    proxy: {
      '/patient': {
        target: 'http://host.docker.internal:20000',
        changeOrigin: true
      },
      '/notes': {
        target: 'http://host.docker.internal:20000',
        changeOrigin: true 
      },
      '/risk-assessment': {
        target: 'http://host.docker.internal:20000',
        changeOrigin: true 
      }
    }
  }
})
