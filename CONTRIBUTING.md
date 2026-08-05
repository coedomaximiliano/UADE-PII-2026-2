# Cómo contribuir

Cada grupo sube su código mediante fork + pull request. Nadie tiene permiso de escritura directa sobre este repositorio.

## Pasos

1. **Fork**: un integrante del grupo hace fork de este repositorio a su cuenta de GitHub (botón "Fork" arriba a la derecha), y agrega al resto del grupo como colaboradores de su fork (`Settings > Collaborators`).

2. **Cloná el fork del grupo**:
   ```
   git clone https://github.com/TU-USUARIO/UADE-PII-2026-2.git
   cd UADE-PII-2026-2
   ```

3. **Creá la carpeta de tu grupo** dentro del trabajo práctico correspondiente:
   ```
   tps/tpN/grupoN/
   ```
   Reemplazá `tpN` por el número de TP y `grupoN` por el número de tu grupo (ej: `grupo3`).

4. **Creá una rama** para la entrega del grupo:
   ```
   git checkout -b tpN-grupoN
   ```

5. **Commiteá y pusheá** a tu fork:
   ```
   git add tps/tpN/grupoN
   git commit -m "TPN: GrupoN"
   git push origin tpN-grupoN
   ```

6. **Abrí un Pull Request** desde el fork del grupo hacia `coedomaximiliano/UADE-PII-2026-2` (rama `main`).

## Reglas

- Subí código **únicamente** dentro de la carpeta de tu propio grupo. No modifiques archivos de otros grupos.
- **Un solo PR por grupo y por entrega**: coordínense entre los integrantes para no pisarse. Correcciones posteriores van como nuevos commits en la misma rama, no un PR nuevo.
- El título del PR debe indicar el TP y el grupo, ej: `TP1: Grupo3`.
