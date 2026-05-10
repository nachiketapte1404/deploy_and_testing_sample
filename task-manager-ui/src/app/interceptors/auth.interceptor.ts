import { HttpInterceptorFn } from '@angular/common/http';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Inject the platform ID to check if we are on server or browser
  const platformId = inject(PLATFORM_ID);

  // ONLY access localStorage if we are in the browser
  if (isPlatformBrowser(platformId)) {
    const token = localStorage.getItem('token');
    console.log('Interceptor checking token:', token); // Add this
    
    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next(authReq);
    }
}

  // If we are on the server or have no token, just pass the request
  return next(req);
};