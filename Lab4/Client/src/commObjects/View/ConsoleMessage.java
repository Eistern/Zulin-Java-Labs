package commObjects.View;

import commObjects.MessageForm;

public class ConsoleMessage implements MessageView {
    @Override
    public void showMessage(MessageForm src) {
        switch (src.getType()) {
            case PRIVATE:
                System.out.println("[" + src.getSrc() + " -> " + src.getDest() + "]: " + src.getData() + "\n");
                break;
            case BROADCAST:
                System.out.println("[" + src.getSrc() + "]: " + src.getData() + "\n");
        }
    }
}
