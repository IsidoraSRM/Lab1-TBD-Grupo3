<script>
    import CreateOrder from '@/components/CreateOrder.vue'; 

    export default {
        name: 'ClientView',
        components: {
        CreateOrder
        },
    }
</script>

<!-- Example: src/views/Home.vue -->
<template>
    <div class="client">
      <h1>cliente page</h1>
      <CreateOrder />
      <!-- Your page content here -->
    </div>
  </template>
  
