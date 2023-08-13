package inventory.event;

import inventory.swing.SearchOption;

public interface SearchOptinEvent {

    public void optionSelected(SearchOption option, int index);
}
