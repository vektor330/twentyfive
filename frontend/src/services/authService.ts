import { useAuth0 } from '@auth0/auth0-vue'

export function useAuthService() {
  const { loginWithRedirect, logout, user, isAuthenticated, getAccessTokenSilently } = useAuth0()

  const login = async () => {
    await loginWithRedirect()
  }

  const logoutUser = () => {
    logout({ logoutParams: { returnTo: window.location.origin } })
  }

  const getToken = async () => {
    return await getAccessTokenSilently()
  }

  return { login, logout: logoutUser, user, isAuthenticated, getToken }
}
