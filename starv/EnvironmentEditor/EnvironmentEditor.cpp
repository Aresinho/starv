// EnvironmentEditior2.cpp : main project file.

#include "stdafx.h"
#include "MapperWindow1.h"

using namespace EnvironmentEditior;

[STAThreadAttribute]
int main(array<System::String ^> ^args)
{
	// Enabling Windows XP visual effects before any controls are created
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false); 

	// Create the main window and run it
	Application::Run(gcnew MapperWindow1());
	return 0;
}
