<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <v-app>
    <header>
      <v-navigation-drawer permanent mini-variant-width="72px" class="pr-4" app clipped>
        <div class="d-inline-flex justify-end align-center">
          <v-img alt="Vue logo" class="mx-3 " src="/logo.png" width="50" height="50" />
          <h2>Clinic App</h2>
        </div>
        <v-list-item to="/" link class="pa-4">
          <v-list-item-content>
            <v-list-item-title class="pl-6">
              <h3>Patient List</h3>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/about" link class="pa-4">
          <v-list-item-content>
            <v-list-item-title class="pl-6">
              <h3>About</h3>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-navigation-drawer>
    </header>

    <v-container fluid>
      <v-main>
        <RouterView />
      </v-main>
    </v-container>
  </v-app>
</template>

<style scoped></style>
