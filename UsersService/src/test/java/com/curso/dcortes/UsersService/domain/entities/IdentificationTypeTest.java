package com.curso.dcortes.UsersService.domain.entities;

import com.curso.dcortes.UsersService.domain.entities.IdentificationType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IdentificationTypeTest {
    @Test
    void identificationTypeShouldHaveCorrectName() {
        assertThat(IdentificationType.NATIONAL.getName()).isEqualTo("NATIONAL");
        assertThat(IdentificationType.PASSPORT.getName()).isEqualTo("PASSPORT");
    }
}
