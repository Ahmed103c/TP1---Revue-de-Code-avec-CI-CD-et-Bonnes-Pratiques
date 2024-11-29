package com.f2.revue_code;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AwesomePasswordCheckerTest 
{
    /**
     * Rigorous Test :-)
     */
    // @Test
    // public void shouldAnswerWithTrue()
    // {
    //     assertTrue( false );
    // }
    @Test
    public void testSingletonInstance() throws IOException {
        // Créer deux instances en appelant getInstance()
        AwesomePasswordChecker instance1 = AwesomePasswordChecker.getInstance();
        AwesomePasswordChecker instance2 = AwesomePasswordChecker.getInstance();
        
        // Vérifier que les deux instances sont identiques (même objet)
        assertSame("Les deux instances doivent être la même", instance1, instance2);
    }
    @Test
    public void testMaskAff() throws IOException{
        // Créer une instance de la classe
        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
        
        // Tester avec un mot de passe exemple
        String password = "password123";
        int[] result = checker.maskAff(password);
        
        // Vérifier que le tableau n'est pas nul et a une longueur attendue
        assertNotNull("Le masque ne doit pas être nul", result);
        // Vérifier que la longueur du tableau est correcte
        assertEquals("La longueur du masque doit être de 28", 28, result.length);
    
        // Définir le tableau attendu
        int[] expected = {2, 1, 1, 1, 2, 1, 1, 2, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    
        // Vérifier que les valeurs du tableau sont celles attendues
        assertArrayEquals("Les valeurs du masque ne correspondent pas aux attentes", expected, result);
    }

    @Test
    public void testGetDistance() throws IOException{
        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
        
        // Tester avec un mot de passe exemple
        String password = "password123";
        double distance = checker.getDIstance(password);
  
        // Vérifier que la distance est un nombre positif
        assertTrue("La distance doit être un nombre positif", distance >= 0);
    }
    @Test
    public void testComputeMD5() {
        // Tester la méthode ComputeMD5
        String input = "password123";
        String expectedMd5 = "482c811da5d5b4bc6d497ffa98491e38"; // Hachage attendu pour "password123"
        
        String actualMd5 = AwesomePasswordChecker.ComputeMD5(input);
        
        // Vérifier que le hachage retourné est correct
        assertEquals("Le hachage MD5 doit correspondre à la valeur attendue", expectedMd5, actualMd5);
    }
}


