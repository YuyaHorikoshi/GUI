package ex13;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest10C extends JFrame {

	ActionListener actionListener = new TextFieldTestAction();
	JTextField tf1, tf2;
	DefaultListModel dlm;
	JButton addButton, updateButton,removeButton;
	JList list;
	JPanel pane;

	public static void main(String[] args) {
		JFrame w = new JListTest10C("JTextFieldTest10C");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(150, 120);
		w.setVisible(true);
	}

	public JListTest10C(String title) {
		super(title);
		JPanel pane = (JPanel) getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		JPanel pane2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane.add(pane2);
		
		addButton = new JButton(new AddAction());
		pane2.add(addButton);
		updateButton = new JButton(new UpdateAction());
		pane2.add(updateButton);
		//removeButton=new JButton(new RemoveAction());
		//pane2.add(removeButton);

		dlm = new DefaultListModel();
		list = new JList(dlm);
		list.setBorder(new TitledBorder("項目一覧"));
		list.addListSelectionListener(new SelectionListener());
		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(list);
		sp.setPreferredSize(new Dimension(200, 80));
		pane.add(sp);

		tf1 = new JTextField();
		tf1.setBorder(new TitledBorder("項目入力"));
		tf1.addActionListener(actionListener);
		pane.add(tf1);

	}
	
	class RemoveAction extends AbstractAction{
		RemoveAction(){
			putValue(Action.NAME,"削除");
			putValue(Action.SHORT_DESCRIPTION,"削除");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			int index=list.getSelectedIndex();
			String s=(String) list.getSelectedValue();
			if(dlm.getSize()==0){
				return;
			}
			Object[] msg={s,"を削除します"};
			int ans=JOptionPane.showConfirmDialog(pane, msg,"削除の確認",JOptionPane.YES_NO_OPTION);
			if(ans==0){
			dlm.remove(index);
			}
		}
		
	}

	class UpdateAction extends AbstractAction {
		UpdateAction() {
			putValue(Action.NAME, "変更");
			putValue(Action.SHORT_DESCRIPTION, "変更");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			String s = tf1.getText();
			int index = list.getSelectedIndex();
			dlm.set(index, s);
		}

	}

	class TextFieldTestAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField source = (JTextField) e.getSource();
			String string = source.getText();
			if (source == tf1) {
				dlm.addElement(string);
				tf1.setText(null);
			}
		}
	}

	class SelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			String s = (String) list.getSelectedValue();
			tf1.setText(s);
		}

	}

	class AddAction extends AbstractAction {
		AddAction() {
			putValue(Action.NAME, "追加");
			putValue(Action.SHORT_DESCRIPTION, "追加");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tf1.getText();
			dlm.addElement(s);
			tf1.setText(null);
		}
	}
}