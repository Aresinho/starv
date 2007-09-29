#pragma once

using namespace System;
using namespace System::ComponentModel;
using namespace System::Collections;
using namespace System::Windows::Forms;
using namespace System::Data;
using namespace System::Drawing;


namespace EnvironmentEditor {

	/// <summary>
	/// Summary for MapperWindow
	///
	/// WARNING: If you change the name of this class, you will need to change the
	///          'Resource File Name' property for the managed resource compiler tool
	///          associated with all .resx files this class depends on.  Otherwise,
	///          the designers will not be able to interact properly with localized
	///          resources associated with this form.
	/// </summary>
	public ref class MapperWindow : public System::Windows::Forms::Form
	{
	public:
		MapperWindow(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MapperWindow()
		{
			if (components)
			{
				delete components;
			}
		}

	protected: 




	private: System::Windows::Forms::ToolStripContainer^  toolStripContainer1;
	private: System::Windows::Forms::ToolStripContainer^  toolStripContainer2;

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->toolStripContainer1 = (gcnew System::Windows::Forms::ToolStripContainer());
			this->toolStripContainer2 = (gcnew System::Windows::Forms::ToolStripContainer());
			this->toolStripContainer1->SuspendLayout();
			this->toolStripContainer2->SuspendLayout();
			this->SuspendLayout();
			// 
			// toolStripContainer1
			// 
			// 
			// toolStripContainer1.ContentPanel
			// 
			this->toolStripContainer1->ContentPanel->Size = System::Drawing::Size(150, 175);
			this->toolStripContainer1->Location = System::Drawing::Point(8, 8);
			this->toolStripContainer1->Name = L"toolStripContainer1";
			this->toolStripContainer1->Size = System::Drawing::Size(150, 175);
			this->toolStripContainer1->TabIndex = 1;
			this->toolStripContainer1->Text = L"toolStripContainer1";
			// 
			// toolStripContainer2
			// 
			this->toolStripContainer2->BottomToolStripPanelVisible = false;
			// 
			// toolStripContainer2.ContentPanel
			// 
			this->toolStripContainer2->ContentPanel->Size = System::Drawing::Size(756, 19);
			this->toolStripContainer2->LeftToolStripPanelVisible = false;
			this->toolStripContainer2->Location = System::Drawing::Point(8, 8);
			this->toolStripContainer2->Name = L"toolStripContainer2";
			this->toolStripContainer2->RightToolStripPanelVisible = false;
			this->toolStripContainer2->Size = System::Drawing::Size(756, 44);
			this->toolStripContainer2->TabIndex = 2;
			this->toolStripContainer2->Text = L"toolStripContainer2";
			// 
			// MapperWindow
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(776, 433);
			this->Controls->Add(this->toolStripContainer2);
			this->Controls->Add(this->toolStripContainer1);
			this->Name = L"MapperWindow";
			this->Text = L"MapperWindow";
			this->toolStripContainer1->ResumeLayout(false);
			this->toolStripContainer1->PerformLayout();
			this->toolStripContainer2->ResumeLayout(false);
			this->toolStripContainer2->PerformLayout();
			this->ResumeLayout(false);

		}
#pragma endregion
	private: System::Void fileToolStripMenuItem_Click(System::Object^  sender, System::EventArgs^  e) {// stuff
			 }
	};
}
