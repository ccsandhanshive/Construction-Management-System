package com.aspect;

import com.client.UserInterface;

public class InterfaceProvider //User Interface Provider
{
public static UserInterface provideObject() {
	return new UserInterface();
}
}
