import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // This allows the host machine to access the containerized Vite dev server.
    port: 5183, // Vite dev server port 
    proxy: {
      '/api/user/me': {
        target: 'http://localhost:20000',
        changeOrigin: true,
        secure: false, 
        cookieDomainRewrite: "localhost"
      },
      '/patient': {target: process.env.REACT_APP_API_GATEWAY_URL, changeOrigin: true, secure: false},
      '/patients': {target: process.env.REACT_APP_API_GATEWAY_URL, changeOrigin: true, secure: false},
      '/notes': {target: process.env.REACT_APP_API_GATEWAY_URL, changeOrigin: true, secure: false},
      '/risk-assessment': {target: process.env.REACT_APP_API_GATEWAY_URL, changeOrigin: true, secure: false}
    }
  }
})
