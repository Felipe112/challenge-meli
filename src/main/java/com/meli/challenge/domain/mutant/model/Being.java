package com.meli.challenge.domain.mutant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Clase modelo de un Ser, el cual posee las caracteristicas comenes, adicional nos permite realizar un map,
 * del modelo de datos.
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-05-31
 * @Version 1.0
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "being")
public class Being implements Serializable {

    private static final long serialVersionUID = 2931158179377448994L;

    /**
     * Id unito de tipo UUID con el fin de que esto facilite la replicación a nivel de infrastructura.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @Type(type = "uuid-char")
    private UUID id;

    /**
     * Cadena de ADN de un Ser
     */
    @Column(name = "dna", nullable = false, unique = true)
    private String dna;

    /**
     * Identificador que permite saber si el ser es un mutante.
     */
    @Column(name = "mutant", nullable = false)
    private boolean mutant;
}
