import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)

app.use(router)
app.use(VueAxios, axios)
app.config.globalProperties.$axios = axios;

axios.defaults.baseURL = "http://localhost:9292"

const vuetify = createVuetify({
    components,
    directives
  })

app.use(vuetify)

app.mount('#app')
