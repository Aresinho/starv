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
	private: System::Windows::Forms::Button^  button1;

	protected: 

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
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->SuspendLayout();
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(13, 27);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(133, 23);
			this->button1->TabIndex = 0;
			this->button1->Text = L"3D Object Pallet";
			this->button1->UseVisualStyleBackColor = true;
			// 
			// MapperWindow
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(292, 266);
			this->Controls->Add(this->button1);
			this->Name = L"MapperWindow";
			this->Text = L"MapperWindow";
			this->Load += gcnew System::EventHandler(this, &MapperWindow::MapperWindow_Load);
			this->ResumeLayout(false);

		}
#pragma endregion
	private: System::Void MapperWindow_Load(System::Object^  sender, System::EventArgs^  e) {
			 }
	};
}
