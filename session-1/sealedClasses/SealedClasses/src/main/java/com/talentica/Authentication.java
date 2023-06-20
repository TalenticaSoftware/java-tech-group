package com.talentica;

public sealed interface Authentication permits AdharCardAuthentication, DrivingLicenceAuthentication, PassportAuthentication {
}
