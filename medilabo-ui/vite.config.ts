import { defineConfig, loadEnv, type ConfigEnv } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/

export default ({mode}: ConfigEnv) => {
  // Load environment variables based on the mode (development, production, etc.)
  const env = loadEnv(mode, process.cwd(), '')
  const API_TARGET = env.VITE_API_GATEWAY_URL

  return defineConfig({
    plugins: [react()],
    server: {
      host: true, // This allows the host machine to access the containerized Vite dev server.
      port: 5183, // Vite dev server port 
      proxy: {
        '/auth': {
          target: API_TARGET,
          changeOrigin: true,
          secure: false,
          cookieDomainRewrite: "localhost"
        },
        '/api/user/me': {
          target: API_TARGET,
          changeOrigin: true,
          secure: false, 
          cookieDomainRewrite: "localhost"
        },
        '/patient': {target: API_TARGET, changeOrigin: true, secure: false},
        '/patients': {target: API_TARGET, changeOrigin: true, secure: false},
        '/notes': {target: API_TARGET, changeOrigin: true, secure: false},
        '/risk-assessment': {target: API_TARGET, changeOrigin: true, secure: false}
      }
    }
  })
}