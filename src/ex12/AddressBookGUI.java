package ex12;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddressBookGUI extends JFrame {
	JTextField nameField, addressField, telField, emailField;
	DefaultListModel model;
	JList list;
	JButton addButton, removeButton, updateButton;
	JPanel pane;
	AddressBook book;

	public static void main(String[] args) {
		JFrame w = new AddressBookGUI("AddressBookGUI");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(400, 300);
		w.setVisible(true);
	}

	public AddressBookGUI(String title) {
		super(title);
		book = new AddressBook();
		pane = (JPanel) getContentPane();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("ファイル");
		menuBar.add(fileMenu);
		JMenuItem item;
		item = new JMenuItem(new OpenAction());
		fileMenu.add(item);
		item = new JMenuItem(new SaveAction());
		fileMenu.add(item);
		fileMenu.addSeparator();
		item = new JMenuItem(new ExitAction());
		fileMenu.add(item);

		model = new DefaultListModel();
		list = new JList(model);
		list.addListSelectionListener(new NameSelect());
		JScrollPane sc = new JScrollPane(list);
		sc.setBorder(new TitledBorder("名前一覧"));
		pane.add(sc, BorderLayout.CENTER);

		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		nameField = new JTextField(20);
		nameField.setBorder(new TitledBorder("名前"));
		fields.add(nameField);
		addressField = new JTextField(20);
		addressField.setBorder(new TitledBorder("住所"));
		fields.add(addressField);
		telField = new JTextField(20);
		telField.setBorder(new TitledBorder("電話"));
		fields.add(telField);
		emailField = new JTextField(20);
		emailField.setBorder(new TitledBorder("メール"));
		fields.add(emailField);

		addButton = new JButton(new AddAction());
		fields.add(addButton);
		updateButton = new JButton(new UpdateAction());
		fields.add(updateButton);
		removeButton = new JButton(new RemoveAction());
		fields.add(removeButton);

		pane.add(fields, BorderLayout.EAST);
	}

	class NameSelect implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				return;
			}
			String s = (String) list.getSelectedValue();
			Address add = book.findName(s);
			nameField.setText(add.getName());
			addressField.setText(add.getAddress());
			telField.setText(add.getTel());
			emailField.setText(add.getEmail());
		}
	}

	class OpenAction extends AbstractAction {
		OpenAction() {
			putValue(Action.NAME, "開く");
			putValue(Action.SHORT_DESCRIPTION, "開く");
		}

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("ファイルを開く");
			int ret = fileChooser.showOpenDialog(pane);
			if (ret != JFileChooser.APPROVE_OPTION)
				return;
			String filename = fileChooser.getSelectedFile().getAbsolutePath();
			book.open(filename);
			model.clear();
			for (String s : book.getNames()) {
				model.addElement(s);
			}
		}
	}

	class SaveAction extends AbstractAction {
		SaveAction() {
			putValue(Action.NAME, "保存");
			putValue(Action.SHORT_DESCRIPTION, "保存");
		}

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int ret = fileChooser.showSaveDialog(list);
			if (ret != JFileChooser.APPROVE_OPTION)
				return;
			String filename = fileChooser.getSelectedFile().getAbsolutePath();
			book.save(filename);
		}
	}

	class ExitAction extends AbstractAction {
		ExitAction() {
			putValue(Action.NAME, "終了");
			putValue(Action.SHORT_DESCRIPTION, "終了");
		}

		public void actionPerformed(ActionEvent e) {
			Object[] msg = { "保存されていないデータは失われますが、", "本当によろしいですか？" };
			int ans = JOptionPane.showConfirmDialog(pane, msg, "EJDicの終了", JOptionPane.YES_NO_OPTION);
			if (ans == 0) {
				System.exit(0);
			}
		}
	}

	class AddAction extends AbstractAction {
		AddAction() {
			putValue(Action.NAME, "追加");
			putValue(Action.SHORT_DESCRIPTION, "追加");
		}

		public void actionPerformed(ActionEvent e) {
			String addN = nameField.getText();
			String addA = addressField.getText();
			String addT = telField.getText();
			String addE = emailField.getText();
			Address adrs = new Address(addN, addA, addT, addE);
			System.out.println(addN + addA + addT + addE);
			// System.out.println(addE + addJ);
			model = (DefaultListModel) list.getModel();
			// dictionary.showWords();
			if (!addN.equals("") && !addA.equals("") && !addT.equals("") && !addE.equals("")) {
				if (!model.contains(addN)) {
					book.add(adrs);
					adrs.setName(addN);
					adrs.setAddress(addA);
					adrs.setTel(addT);
					adrs.setEmail(addE);
					model.addElement(addN);
					nameField.setText(null);
					addressField.setText(null);
					telField.setText(null);
					emailField.setText(null);
				}
			}
		}
	}

	class UpdateAction extends AbstractAction {
		UpdateAction() {
			putValue(Action.NAME, "更新");
			putValue(Action.SHORT_DESCRIPTION, "更新");
		}

		public void actionPerformed(ActionEvent e) {
			String s = nameField.getText();
			System.out.println(s);
			model = (DefaultListModel) list.getModel();
			if (!s.equals("")) {
				if (model.contains(s)) {
					Address add = book.findName(s);
					String name = add.getName();
					String address = add.getAddress();
					String tel = add.getTel();
					String email = add.getEmail();
					nameField.setText(name);
					addressField.setText(address);
					telField.setText(tel);
					emailField.setText(email);
				}
			}
		}
	}

	class RemoveAction extends AbstractAction {
		RemoveAction() {
			putValue(Action.NAME, "削除");
			putValue(Action.SHORT_DESCRIPTION, "削除");
		}

		public void actionPerformed(ActionEvent e) {
			int index = list.getSelectedIndex();

			if (index == -1) {
				return;
			}
			String s = (String) list.getSelectedValue();
			// System.out.println(list);
			Object[] msg = { "選択したデータを削除しますが、", "よろしいですか？" };

			int ans = JOptionPane.showConfirmDialog(pane, msg, "データの削除", JOptionPane.YES_NO_OPTION);
			if (ans == 0) {
				book.remove(book.findName(s));
				model.remove(index);
				nameField.setText(null);
				addressField.setText(null);
				telField.setText(null);
				emailField.setText(null);

			}
		}
	}

}