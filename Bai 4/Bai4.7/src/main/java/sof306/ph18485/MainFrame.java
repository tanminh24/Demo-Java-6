package sof306.ph18485;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sof306.ph18485.beans.Student;
import sof306.ph18485.beans.StudentMap;
import sof306.ph18485.repositories.StudentRepository;

public class MainFrame extends javax.swing.JFrame {

	private StudentRepository studentRepo;
	private DefaultTableModel model;
	private StudentMap items;
	private String key = null;

	public MainFrame() {
		initComponents();
		setTitle("RESTful");
		setLocationRelativeTo(null);
		this.studentRepo = new StudentRepository();
		this.model = (DefaultTableModel) this.tblStudent.getModel();
		this.load();
		this.reset();
	}

	private void load() {
		try {
			this.items = this.studentRepo.findAll();
			this.fillTable();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu");
		}
	}

	private void fillTable() {
		this.model.setRowCount(0);
		items.forEach((k, st) -> {
			Object[] row = new Object[] { st.getEmail(), st.getFullname(), st.getMarks(),
					st.getGender() ? "Male" : "Female", st.getCountry() };
			this.model.addRow(row);
		});
	}

	private void create() {
		try {
			Student student = this.getForm();
			this.key = this.studentRepo.create(student);
			this.items.put(this.key, student);
			JOptionPane.showMessageDialog(this, "Tạo mới thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Tạo mới thất bại");
		}
	}

	private void update() {
		try {
			Student student = this.getForm();
			this.studentRepo.update(this.key, student);
			this.items.put(this.key, student);
			JOptionPane.showMessageDialog(this, "Cập nhật thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
		}
	}

	private void delete() {
		try {
			this.studentRepo.delete(this.key);
			this.items.remove(this.key);
			this.reset();
			JOptionPane.showMessageDialog(this, "Xoá thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Xoá thành công");
		}
	}
	
	private void edit(String key) {
		try {
			Student student = studentRepo.findByKey(key);
			this.key = key;
			this.setForm(student);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Lỗi tải dữ liệu");
		}
	}

	private void reset() {
		Student student = new Student();
		student.setEmail("");
		student.setFullname("");
		student.setMarks(0.0);
		student.setGender(true);
		student.setCountry("VN");
		this.setForm(student);
		this.key = null;
	}

	private Student getForm() {
		Student student = new Student();
		student.setEmail(this.txtEmail.getText());
		student.setFullname(this.txtFullname.getText());
		student.setMarks(Double.parseDouble(this.txtMarks.getText()));
		student.setGender(this.rdoMale.isSelected());
		student.setCountry(this.cbbCountry.getSelectedItem().toString());
		return student;
	}

	private void setForm(Student student) {
		this.txtEmail.setText(student.getEmail());
		this.txtFullname.setText(student.getFullname());
		this.txtMarks.setText(student.getMarks().toString());
		this.rdoMale.setSelected(student.getGender());
		this.cbbCountry.setSelectedItem(student.getCountry());
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblStudent = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		txtFullname = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		txtMarks = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		rdoMale = new javax.swing.JRadioButton();
		rdoFemale = new javax.swing.JRadioButton();
		jLabel6 = new javax.swing.JLabel();
		cbbCountry = new javax.swing.JComboBox<>();
		btnCreate = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		btnReset = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		tblStudent.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Email", "Fullname", "Marks", "Gender", "Country" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblStudentMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tblStudent);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE));

		jTabbedPane1.addTab("LIST", jPanel1);

		jLabel2.setText("Email");

		jLabel3.setText("Fullname");

		jLabel4.setText("Marks");

		jLabel5.setText("Gender");

		buttonGroup1.add(rdoMale);
		rdoMale.setText("Male");

		buttonGroup1.add(rdoFemale);
		rdoFemale.setText("Female");

		jLabel6.setText("Country");

		cbbCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VN", "US" }));

		btnCreate.setText("Create");
		btnCreate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCreateActionPerformed(evt);
			}
		});

		btnUpdate.setText("Update");
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});

		btnDelete.setText("Delete");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
		});

		btnReset.setText("Reset");
		btnReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnResetActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(txtEmail)
						.addComponent(txtFullname).addComponent(txtMarks)
						.addComponent(cbbCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 272, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel4)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtMarks, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(rdoMale).addComponent(rdoFemale))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cbbCountry, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCreate).addComponent(btnUpdate).addComponent(btnDelete)
								.addComponent(btnReset))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jTabbedPane1.addTab("EDIT", jPanel2);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("RESTful Consumer - URL");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jTabbedPane1))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jTabbedPane1).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if (evt.getClickCount() == 2) {
			int index = this.tblStudent.getSelectedRow();
			String key = (String) items.keySet().toArray()[index];
			this.edit(key);
			jTabbedPane1.setSelectedIndex(1);
		}
	}

	private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.create();
		this.reset();
		this.load();
	}

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.update();
		this.load();
	}

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.delete();
		this.reset();
		this.load();
	}

	private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.reset();
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCreate;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnReset;
	private javax.swing.JButton btnUpdate;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JComboBox<String> cbbCountry;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JRadioButton rdoFemale;
	private javax.swing.JRadioButton rdoMale;
	private javax.swing.JTable tblStudent;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtFullname;
	private javax.swing.JTextField txtMarks;
	// End of variables declaration//GEN-END:variables
}
