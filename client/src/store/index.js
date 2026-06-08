import { defineStore } from 'pinia'
import { ref } from 'vue'

/**
 * 管理员状态管理
 */
export const useAdminStore = defineStore('admin', () => {
  const admin = ref(JSON.parse(localStorage.getItem('admin') || 'null'))

  function setAdmin(data) {
    admin.value = data
    localStorage.setItem('admin', JSON.stringify(data))
  }

  function logout() {
    admin.value = null
    localStorage.removeItem('admin')
  }

  return { admin, setAdmin, logout }
})
