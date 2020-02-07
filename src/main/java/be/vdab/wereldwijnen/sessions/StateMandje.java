package be.vdab.wereldwijnen.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class StateMandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean gevuld;

    public boolean isGevuld() {
        return gevuld;
    }

    public void setGevuld(boolean gevuld) {
        this.gevuld = gevuld;
    }
}
