import { createApp } from 'vue'
import { createAuth0 } from '@auth0/auth0-vue'
import PrimeVue from 'primevue/config'
import App from './App.vue'
import router from './router'
import * as Sentry from '@sentry/vue'

import 'primevue/resources/themes/lara-light-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'

import './assets/global.css'
import { API_BASE_URL } from './config'

const app = createApp(App)

app.use(PrimeVue)
app.use(router)

app.use(
  createAuth0({
    domain: import.meta.env.VITE_AUTH0_DOMAIN as string,
    clientId: import.meta.env.VITE_AUTH0_CLIENT_ID as string,
    cacheLocation: 'localstorage',
    useRefreshTokens: true,
    authorizationParams: {
      redirect_uri: window.location.origin,
      scope: 'openid profile email offline_access',
      audience: API_BASE_URL.production,
    },
  }),
)

if (import.meta.env.PROD) {
  Sentry.init({
    app,
    dsn: import.meta.env.VITE_SENTRY_DSN,
    integrations: [Sentry.browserTracingIntegration({ router })],
  })
}

app.mount('#app')
