package org.sigar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sigar.model.Piece;

@Setter
@AllArgsConstructor
@Getter
public class Player{
    private String name;
    private Piece piece;
}
