import { ref } from 'vue'
import { API_BASE_URL } from '../config'
import { useAuth0 } from '@auth0/auth0-vue'

export interface Picture {
  id: string
  url: string
}

export const TOTAL_IMAGES = 25

export function useGalleryService() {
  const images = ref<(Picture | null)[]>(Array(TOTAL_IMAGES).fill(null))
  const healthCode = ref<number | null>(null)
  const { getAccessTokenSilently, isAuthenticated } = useAuth0()

  async function loadGallery(userSlug?: string, gallerySlug?: string) {
    const apiBaseUrl = import.meta.env.DEV ? API_BASE_URL.development : API_BASE_URL.production

    try {
      const healthResponse = await fetch(`${apiBaseUrl}/health`)
      healthCode.value = healthResponse.status

      const params = new URLSearchParams()
      if (userSlug) params.append('user', userSlug)
      if (gallerySlug) params.append('gallery', gallerySlug)
      const queryString = params.toString()

      const headers: Record<string, string> = {}

      // Only add auth token if user is authenticated
      if (isAuthenticated.value) {
        const token = await getAccessTokenSilently()
        headers['Authorization'] = `Bearer ${token}`
      }

      const galleryResponse = await fetch(
        `${apiBaseUrl}/gallery${queryString ? '?' + queryString : ''}`,
        {
          headers,
        },
      )
      const galleryData = await galleryResponse.json()
      const fetchedImages = galleryData.pictures as Picture[]

      images.value = Array(TOTAL_IMAGES).fill(null)

      fetchedImages.forEach((img, index) => {
        if (index < TOTAL_IMAGES) {
          images.value[index] = img
        }
      })
    } catch (error) {
      console.error('Error fetching data:', error)
      healthCode.value = 0
    }
  }

  async function uploadPicture(file: File, position: number) {
    const apiBaseUrl = import.meta.env.DEV ? API_BASE_URL.development : API_BASE_URL.production
    const formData = new FormData()
    formData.append('file', file)
    formData.append('position', position.toString())
    const headers: Record<string, string> = {}
    // TODO refactoring: we should obviously not do anything if the user is not authenticated
    if (isAuthenticated.value) {
      const token = await getAccessTokenSilently()
      headers['Authorization'] = `Bearer ${token}`
    }
    const response = await fetch(`${apiBaseUrl}/upload`, {
      method: 'POST',
      body: formData,
      headers,
      credentials: 'include', // TODO: check if this is needed
    })
    if (!response.ok) {
      throw new Error('Upload failed')
    }
    return await response.json()
  }

  return { images, healthCode, loadGallery, uploadPicture }
}
