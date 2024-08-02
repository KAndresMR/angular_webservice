import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';

/**
 * Configuración de la aplicación Angular.
 * 
 * - `provideZoneChangeDetection`: Configura la detección de cambios en Angular.
 * - `provideRouter`: Proporciona el enrutador de la aplicación con las rutas definidas.
 * - `provideClientHydration`: Habilita la hidratación del cliente en la aplicación.
 * - `provideHttpClient`: Configura el cliente HTTP con opciones específicas.
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), // Configura la detección de cambios para mejorar el rendimiento
    provideRouter(routes), // Proporciona el enrutador con las rutas definidas
    provideClientHydration(), // Habilita la hidratación del cliente para una mejor integración con el servidor
    provideHttpClient(withFetch()) // Configura el cliente HTTP usando `fetch` y las interceptaciones definidas
  ]
};