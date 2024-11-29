package org.sigar.core.validators;
import org.sigar.core.gameArea.GameArea;
import org.sigar.core.gameArea.location.GameLocation;

public interface Validator {
    boolean validateLocation(GameArea area, GameLocation location);
}
