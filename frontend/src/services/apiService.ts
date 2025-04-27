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
  const { getAccessTokenSilently } = useAuth0()

  async function loadGallery(userSlug?: string, gallerySlug?: string) {
    const apiBaseUrl = import.meta.env.DEV ? API_BASE_URL.development : API_BASE_URL.production

    try {
      const healthResponse = await fetch(`${apiBaseUrl}/health`)
      healthCode.value = healthResponse.status

      const token = await getAccessTokenSilently()

      const params = new URLSearchParams()
      if (userSlug) params.append('user', userSlug)
      if (gallerySlug) params.append('gallery', gallerySlug)
      const queryString = params.toString()

      const galleryResponse = await fetch(
        `${apiBaseUrl}/gallery${queryString ? '?' + queryString : ''}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
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

  return { images, healthCode, loadGallery }
}
