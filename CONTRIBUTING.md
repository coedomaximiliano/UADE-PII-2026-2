# Cómo contribuir

Cada alumno sube su código mediante fork + pull request. Nadie tiene permiso de escritura directa sobre este repositorio.

## Pasos

1. **Fork**: hacé fork de este repositorio a tu cuenta de GitHub (botón "Fork" arriba a la derecha).

2. **Cloná tu fork**:
   ```
   git clone https://github.com/TU-USUARIO/UADE-PII-2026-2.git
   cd UADE-PII-2026-2
   ```

3. **Creá tu carpeta** dentro del trabajo práctico correspondiente:
   ```
   tps/tpN/apellido-nombre/
   ```
   Reemplazá `tpN` por el número de TP y `apellido-nombre` por tu apellido y nombre (todo en minúsculas, separado por guiones).

4. **Creá una rama** para tu entrega:
   ```
   git checkout -b tpN-apellido-nombre
   ```

5. **Commiteá y pusheá** a tu fork:
   ```
   git add tps/tpN/apellido-nombre
   git commit -m "TPN: apellido nombre"
   git push origin tpN-apellido-nombre
   ```

6. **Abrí un Pull Request** desde tu fork hacia `coedomaximiliano/UADE-PII-2026-2` (rama `main`).

## Reglas

- Subí código **únicamente** dentro de tu propia carpeta. No modifiques archivos de otros alumnos.
- Un PR por entrega/trabajo práctico.
- El título del PR debe indicar el TP y tu nombre, ej: `TP1: Pérez Juan`.
