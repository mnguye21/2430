/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.util.*;
import java.util.Iterator;

public class IteratorReferenceDemo
{
    public static void main(String[] args)
    {
        //Collection<Date> birthdays = new HashSet<Date>();
        Collection<Date> birthdays = new ArrayList<Date>();

        birthdays.add(new Date(1, 1, 1990));
        birthdays.add(new Date(2, 2, 1990));
        birthdays.add(new Date(3, 3, 1990));

        System.out.println("The list contains:");

        //for (int i = 0; i < birthdays.size(); i++)
        //    System.out.println(birthdays.get(i));

        Iterator<Date> i = birthdays.iterator( );
        while (i.hasNext( ))
            System.out.println(i.next( ));

        i = birthdays.iterator( );
        Date d = null; //To keep the compiler happy.
        System.out.println("Changing the references.");
        while (i.hasNext( ))
        {
            d = i.next( );
            d.setDate(4, 1, 1990);
        }


        System.out.println("The list now contains:");


        i = birthdays.iterator( );
        while (i.hasNext( ))
            System.out.println(i.next( ));


        System.out.println("April fool!");
    }
}


