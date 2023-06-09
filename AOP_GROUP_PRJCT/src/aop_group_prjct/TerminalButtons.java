package aop_group_prjct;

import java.util.Arrays;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class TerminalButtons {

    public TerminalButtons() {
    }

    public void DeleteFromFormattedTextField(JFormattedTextField textField) {
        String phoneInputRes = textField.getValue().toString();
        if (phoneInputRes.charAt(phoneInputRes.length() - 1) == '-' || phoneInputRes.charAt(phoneInputRes.length() - 1) == ')') {
            phoneInputRes = phoneInputRes.substring(0, phoneInputRes.length() - 1);
            textField.setValue(phoneInputRes);
        }
        if (phoneInputRes.length() >= 1) {
            phoneInputRes = phoneInputRes.substring(0, phoneInputRes.length() - 1);
            textField.setValue(phoneInputRes);
        }
    }

    public void AddDigitToFormattedTextField(JFormattedTextField textField, int digit) {
        String phoneInputRes = textField.getValue().toString();

        if (phoneInputRes.length() == 0) {
            textField.setValue("(" + digit);
        } else if (phoneInputRes.length() == 4) {
            textField.setValue(phoneInputRes + ")" + digit);
        } else if (phoneInputRes.length() == 7) {
            textField.setValue(phoneInputRes + "-" + digit);
        } else if (phoneInputRes.length() == 11) {
            textField.setValue(phoneInputRes + "-" + digit);
        } else if (phoneInputRes.length() >= 16) {
            return;
        } else {
            textField.setValue(phoneInputRes + digit);
//            System.out.println("Len: " + phoneInputRes.length());
        }

//        System.out.println("RES: " + textField.getValue().toString());
    }

    public void AddDigitToPasswordField(JPasswordField textField, int digit) {
        if (textField.getPassword().length < 4) {
            char[] phoneInputCharRes = textField.getPassword();
            String phoneInputStringRes = new String(phoneInputCharRes);
            textField.setText(phoneInputStringRes + digit);
            System.out.println("RES: " + new String(textField.getPassword()));
        }
    }

    public void DeleteFromPasswordField(JPasswordField textField) {
        if (textField.getPassword().length > 0) {
            char[] phoneInputCharRes = textField.getPassword();
            String phoneInputStringRes = new String(phoneInputCharRes);
            textField.setText(phoneInputStringRes.substring(0, phoneInputStringRes.length() - 1));
            System.out.println("RES: " + new String(textField.getPassword()));
        }
    }

    public void AddDigitToCardField(JFormattedTextField textField, int digit) {
        String cardInputRes;
        try {
            cardInputRes = textField.getValue().toString();
        } catch (NullPointerException e) {
            textField.setValue("");
            cardInputRes = textField.getValue().toString();
        }

        if (cardInputRes.length() == 4) {
            textField.setValue(cardInputRes + "-" + digit);
        } else if (cardInputRes.length() == 9) {
            textField.setValue(cardInputRes + "-" + digit);
        } else if (cardInputRes.length() == 14) {
            textField.setValue(cardInputRes + "-" + digit);
        } else if (cardInputRes.length() >= 19) {
        } else {
            textField.setValue(cardInputRes + digit);
        }
//        System.out.println("RESF: " + textField.getValue().toString());
    }

    public void DeleteFromValueField(JFormattedTextField textField) {
        String textInput;
        try {
            textInput = textField.getValue().toString();
        } catch (NullPointerException e) {
            textField.setValue("");
            textInput = textField.getValue().toString();
        }
        if (textInput.length() > 0) {
            textInput = textInput.substring(0, textInput.length() - 1);
            textField.setValue(textInput);
        }
    }

    public void AddDigitToValueField(JFormattedTextField textField, int digit) {
        if (textField.getValue() == null || textField.getValue().toString().equals("0")) {
            textField.setValue("" + digit);
        } else {
            if (textField.getValue().toString().length() <= 5) {
                textField.setValue(textField.getValue() + "" + digit);
            }
        }
    }

    public void addDigitToTransferCardField(JFormattedTextField textField, int digit, JLabel receiverNameLabel, User currentUser, JPanel keybord) {
        AddDigitToCardField(textField, digit);

        ConnectDB DataBaseObject = new ConnectDB();
        String cardInputResult;

        cardInputResult = textField.getValue().toString();
        System.out.println("CARD: " + cardInputResult.length());
        if (cardInputResult.length() == 19) {
            User cardUser = null;
            List<User> users = DataBaseObject.SelectAll();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getCard_Number().equals(cardInputResult)) {
                    cardUser = users.get(i);
                    System.out.println("USER NAME: " + cardUser.getUser_name());
                }
            }
            if (cardUser != null && !cardUser.getCard_Number().equals(currentUser.getCard_Number())) {
                receiverNameLabel.setText("Receiver: " + cardUser.getUser_name());

                keybord.setVisible(false);
            } else {
                receiverNameLabel.setText("Receiver: No such receiver");
            }
        }

    }
}
