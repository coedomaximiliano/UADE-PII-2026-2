// TDA Cola con Prioridad
// Criterio de desempate: si dos elementos tienen la misma prioridad, se
// respeta el orden de llegada (FIFO): el que fue insertado primero es
// extraído primero.

// crear: -> ColaConPrioridad
// post: se devuelve una Cola con Prioridad vacía.

// insertar: ColaConPrioridad x Elemento x Entero -> ColaConPrioridad
// pre:  hay lugar disponible (la cola con prioridad no está llena).
// post: el elemento queda agregado con la prioridad indicada; si ya
//       existían elementos con esa misma prioridad, el nuevo queda
//       "después" de ellos a los efectos del desempate.

// extraerMax: ColaConPrioridad -> ColaConPrioridad x Elemento
// pre:  la cola con prioridad tiene al menos un elemento.
// post: se quita de la cola y se devuelve el elemento de mayor
//       prioridad (el más antiguo entre los que empatan); el resto de
//       los elementos y sus prioridades no cambian.

// verMax: ColaConPrioridad -> Elemento
// pre:  la cola con prioridad tiene al menos un elemento.
// post: se devuelve el elemento de mayor prioridad (el más antiguo
//       entre los que empatan), sin modificar la cola.

// esVacia: ColaConPrioridad -> Booleano
// post: devuelve true si y solo si la cola con prioridad no tiene
//       elementos.

