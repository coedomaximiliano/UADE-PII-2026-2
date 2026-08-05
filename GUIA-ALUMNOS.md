# Guía paso a paso: cómo subir el TP de tu grupo (primera vez con Git)

Esta guía es para quienes nunca usaron Git ni GitHub. Vas a necesitar una cuenta de GitHub (gratis) y tener Git instalado en tu computadora.

Hay dos caminos: **GitHub Desktop** (con interfaz gráfica, más fácil para empezar) o **terminal** (línea de comandos). Elegí el que te resulte más cómodo, el resultado es el mismo.

---

## 0. Requisitos previos

1. **Creá una cuenta en GitHub** (si no tenés): https://github.com/join
2. **Instalá Git**: https://git-scm.com/downloads (dejá todas las opciones por defecto durante la instalación)
3. *(Opcional, recomendado para principiantes)* **Instalá GitHub Desktop**: https://desktop.github.com/

---

## 1. Organización del grupo

Las entregas son por grupo: **un solo fork y una sola rama por grupo**, no uno por cada integrante. Antes de empezar, pónganse de acuerdo:

- **Un integrante hace el fork** del repositorio (pasos más abajo).
- Ese integrante agrega a sus compañeros como colaboradores de su fork: en su fork en GitHub, `Settings > Collaborators > Add people`, buscando el usuario de GitHub de cada compañero.
- Con eso, todos los integrantes pueden clonar el mismo fork y pushear a la misma rama. Avisen en el grupo antes de pushear si están trabajando en simultáneo, para no pisarse.

---

## Camino A: con GitHub Desktop (recomendado si es tu primera vez)

### 1. Hacé fork del repositorio (solo un integrante del grupo)

1. Andá a https://github.com/coedomaximiliano/UADE-PII-2026-2
2. Hacé click en el botón **Fork** (arriba a la derecha).
3. Confirmá con **Create fork**. Ahora tenés tu propia copia en `https://github.com/TU-USUARIO/UADE-PII-2026-2`.
4. Agregá a tus compañeros de grupo como colaboradores de tu fork (`Settings > Collaborators`).

### 2. Cloná el fork del grupo con GitHub Desktop

1. Abrí GitHub Desktop e iniciá sesión con tu cuenta de GitHub (`File > Options > Sign in`).
2. `File > Clone repository`.
3. Elegí el fork del grupo (`TU-USUARIO/UADE-PII-2026-2`, donde `TU-USUARIO` es el usuario de quien hizo el fork) y una carpeta local donde guardarlo. Click en **Clone**.

### 3. Creá la carpeta del grupo y agreguen sus archivos

1. Abrí la carpeta local del repo (en GitHub Desktop: `Repository > Show in Explorer`).
2. Entrá a `tps/tpN/` (reemplazá `N` por el número del trabajo práctico que están entregando).
3. Creá una carpeta con el número de grupo, por ejemplo `tps/tp1/grupo3/`.
4. Copien ahí los archivos del TP (todos los integrantes trabajan sobre la misma carpeta).

### 4. Creá una rama

1. En GitHub Desktop, arriba dice "Current branch". Click ahí y luego **New branch**.
2. Nombrala `tpN-grupoN` (ej: `tp1-grupo3`).
3. Click en **Create branch**.

### 5. Commit y push

1. GitHub Desktop va a mostrar los archivos nuevos que agregaste (columna izquierda).
2. Abajo a la izquierda, escribí un mensaje de commit, ej: `TP1: Grupo3`.
3. Click en **Commit to tp1-grupo3**.
4. Arriba a la derecha, click en **Push origin** (esto sube los cambios al fork del grupo en GitHub).

### 6. Abrí el Pull Request (una sola vez por grupo)

1. En GitHub Desktop aparece un botón **Create Pull Request** después del push (o entrá al fork en github.com).
2. Se abre GitHub en el navegador. Verificá que:
   - **base repository**: `coedomaximiliano/UADE-PII-2026-2`, rama `main`
   - **head repository**: `TU-USUARIO/UADE-PII-2026-2`, rama `tpN-grupoN`
3. Título del PR: `TP1: Grupo3` (número de TP + número de grupo).
4. Click en **Create pull request**.

¡Listo! Ya está entregado. El docente va a revisar y puede pedir cambios o aprobarlo.

---

## Camino B: con terminal (Git Bash / PowerShell)

### 1. Configurá Git (solo la primera vez que usás Git en tu PC)

```
git config --global user.name "Tu Nombre"
git config --global user.email "tu-email@ejemplo.com"
```

### 2. Hacé fork del repositorio (solo un integrante del grupo)

1. Andá a https://github.com/coedomaximiliano/UADE-PII-2026-2
2. Click en **Fork** (arriba a la derecha) y confirmá con **Create fork**.
3. Agregá a tus compañeros como colaboradores del fork (`Settings > Collaborators`).

### 3. Cloná el fork del grupo

```
git clone https://github.com/TU-USUARIO/UADE-PII-2026-2.git
cd UADE-PII-2026-2
```

(Reemplazá `TU-USUARIO` por el usuario de quien hizo el fork del grupo)

### 4. Creá una rama para la entrega del grupo

```
git checkout -b tp1-grupo3
```

(Reemplazá `tp1-grupo3` por `tpN-grupoN` según corresponda)

### 5. Creá la carpeta del grupo y agreguen sus archivos

Creá la carpeta `tps/tp1/grupo3/` (con el número de TP y el número de grupo) y copien ahí los archivos, entre todos los integrantes.

### 6. Agregá, commiteá y pusheá los cambios

```
git add tps/tp1/grupo3
git commit -m "TP1: Grupo3"
git push origin tp1-grupo3
```

La primera vez que hagas `push` es posible que se abra una ventana del navegador pidiéndote iniciar sesión en GitHub. Iniciá sesión y el push va a continuar solo.

### 7. Abrí el Pull Request (una sola vez por grupo)

1. Terminada la push, la terminal te va a mostrar un link para crear el Pull Request (o entrá al fork en github.com, va a aparecer un botón **Compare & pull request**).
2. Verificá que la base sea `coedomaximiliano/UADE-PII-2026-2` rama `main`.
3. Título del PR: `TP1: Grupo3`.
4. Click en **Create pull request**.

---

## Flujo del día a día mientras programan

Esto se repite cada vez que alguien del grupo se sienta a programar, una vez que ya existe la rama `tpN-grupoN`:

1. **Pararse en la rama correcta**:
   ```
   git checkout tpN-grupoN
   ```
2. **Traer lo que subieron los compañeros**, antes de tocar nada:
   ```
   git pull origin tpN-grupoN
   ```
3. **Programar** dentro de `tps/tpN/grupoN/`.
4. **Guardar el avance** con commits chicos y seguidos (no uno solo gigante al final):
   ```
   git add tps/tpN/grupoN
   git commit -m "agrego validación de X"
   ```
5. **Subir el avance**:
   ```
   git push origin tpN-grupoN
   ```
   El primer push sugiere crear el Pull Request; los siguientes pushes a la misma rama lo actualizan solos, sin hacer nada más.

Repitan los pasos 2 a 5 cada sesión de trabajo. El paso 2 (`pull` antes de empezar) es el que evita la mayoría de los conflictos cuando son varios tocando el mismo archivo.

*Si usan GitHub Desktop, es el mismo flujo con los botones equivalentes: **Fetch origin / Pull origin** antes de programar, y **Commit** + **Push origin** después de cada avance.*

### Si te aparece un conflicto al hacer `pull`

Pasa cuando vos y un compañero modificaron las mismas líneas de un mismo archivo. Git no elige por ustedes — hay que resolverlo a mano:

1. Después de correr `git pull origin tpN-grupoN`, git va a avisar que hay un conflicto y va a marcar el/los archivo(s) afectado(s).
2. Abrí el archivo en conflicto. Vas a ver algo así:
   ```
   <<<<<<< HEAD
   tu versión del código
   =======
   la versión de tu compañero
   >>>>>>> tpN-grupoN
   ```
3. Editá el archivo a mano: dejá el código que corresponda (el tuyo, el de tu compañero, o una combinación de ambos) y borrá las marcas `<<<<<<<`, `=======` y `>>>>>>>`.
4. Guardá el archivo y marcá el conflicto como resuelto:
   ```
   git add tps/tpN/grupoN
   git commit
   ```
   (git arma un mensaje de commit por default para el merge; con aceptarlo alcanza)
5. Ahora sí, subí los cambios:
   ```
   git push origin tpN-grupoN
   ```

Si no están seguros de qué versión dejar, hablen por fuera de git (WhatsApp, en persona) antes de resolver — es más rápido que adivinar.

---

## Si tenés que corregir algo después de entregar

No hace falta abrir un PR nuevo: alcanza con modificar los archivos en la carpeta local, y repetir el `commit` + `push` en la **misma rama**. El Pull Request se actualiza solo con los nuevos cambios. Si son varios integrantes pusheando, avisen en el grupo antes de hacer `push` para no pisarse.

```
git add tps/tp1/grupo3
git commit -m "TP1: correcciones"
git push origin tp1-grupo3
```

---

## Problemas comunes

- **"Nombre de carpeta ya existe" o error al abrir PR**: asegurate de no haber tocado archivos fuera de la carpeta de tu grupo.
- **No me aparece el botón "Create pull request"**: entrá directamente a `https://github.com/TU-USUARIO/UADE-PII-2026-2/pull/new/tpN-grupoN`.
- **Me pide usuario y contraseña y falla**: GitHub ya no acepta contraseña por línea de comandos. Dejá que se abra la ventana del navegador para iniciar sesión (Git Credential Manager), o usá GitHub Desktop.
- **No puedo pushear al fork de mi compañero**: pedile que te agregue como colaborador en `Settings > Collaborators` de su fork.

Ante cualquier duda, consultá al docente antes de la fecha límite de entrega.
