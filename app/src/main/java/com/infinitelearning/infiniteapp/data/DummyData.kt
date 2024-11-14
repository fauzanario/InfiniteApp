package com.infinitelearning.infiniteapp.data

import com.infinitelearning.infiniteapp.R
import com.infinitelearning.infiniteapp.model.Course
import com.infinitelearning.infiniteapp.model.Gallery
import com.infinitelearning.infiniteapp.model.Mentee
import com.infinitelearning.infiniteapp.model.Mentor

object DummyData {
    val mobileMentors = listOf(
        Mentor(
            id = 1,
            name = "Darma Fauzan W.",
            nickname = "Darma",
            role = "Ketua Umum",
            photo = R.drawable.darma
        ),
        Mentor(
            id = 2,
            name = "Akbar Nugraha D.",
            nickname = "Barto",
            role = "Wakil Ketua Umum",
            photo = R.drawable.akbar
        ),
        Mentor(
            id = 3,
            name = "Gina Khairunnisa",
            nickname = "Gina",
            role = "Sekretaris Umum",
            photo = R.drawable.gina
        ),
        Mentor(
            id = 4,
            name = "Alustina Suci M.",
            nickname = "Alus",
            role = "Wakil Sekretaris Umum",
            photo = R.drawable.alus
        ),
        Mentor(
            id = 5,
            name = "Tedi Alamsyah",
            nickname = "Tedi",
            role = "Bendahara Umum",
            photo = R.drawable.tedi
        ),
        Mentor(
            id = 6,
            name = "Najma Alfisyahrina",
            nickname = "Najma",
            role = "Wakil Bendahara Umum",
            photo = R.drawable.najma
        )
    )

    val mobileMentees = listOf(
        Mentee(
            id = 1,
            name = "Dwi Miftahussalamah",
            photo = R.drawable.dwi,
            batch = "PSDM",
            role = "Koordinator Bidang",
        ),
        Mentee(
            id = 2,
            name = "Reza Aditya S.",
            photo = R.drawable.reza,
            batch = "Kominfo",
            role = "Koordinator Bidang",
        ),
        Mentee(
            id = 3,
            name = "Bayu Ragil P.",
            photo = R.drawable.bayu,
            batch = "MinBat",
            role = "Koordinator Bidang",
        ),
        Mentee(
            id = 4,
            name = "Ilhan Yusuf A.",
            photo = R.drawable.ilham,
            batch = "SosPol",
            role = "Koordinator Bidang",
        )
    )

    val mobileCourses = listOf(
        Course(
            id = 1,
            name = "Kaderisasi",
            level = "PSDM",
            photo = R.drawable.kaderisasi
        ),
        Course(
            id = 2,
            name = "Pengembangan & Pengawasan",
            level = "PSDM",
            photo = R.drawable.pp
        ),
        Course(
            id = 3,
            name = "Kaderisasi",
            level = "PSDM",
            photo = R.drawable.kaderisasi
        ),
        Course(
            id = 4,
            name = "Pengembangan & Pengawasan",
            level = "PSDM",
            photo = R.drawable.pp
        ),
        Course(
            id = 5,
            name = "Pengembangan & Pengawasan",
            level = "PSDM",
            photo = R.drawable.pp
        ),
        Course(
            id = 6,
            name = "Kaderisasi",
            level = "PSDM",
            photo = R.drawable.kaderisasi
        ),
        Course(
            id = 7,
            name = "Pengembangan & Pengawasan",
            level = "PSDM",
            photo = R.drawable.pp
        ),
        Course(
            id = 8,
            name = "Kaderisasi",
            level = "PSDM",
            photo = R.drawable.kaderisasi
        )
    )

    val infiniteGalleries = listOf(
        Gallery(
            id = 1,
            name = "DORAA Kuliah",
            photo = R.drawable.doraa
        ),
        Gallery(
            id = 2,
            name = "Seminar Literasi Intelektual Mahasiswa",
            photo = R.drawable.slim
        ),
        Gallery(
            id = 3,
            name = "PKKMB Fasilkom 2024",
            photo = R.drawable.pkkmb
        ),
        Gallery(
            id = 4,
            name = "Inaugurasi 2024",
            photo = R.drawable.inau
        ),
        Gallery(
            id = 5,
            name = "Studi Banding",
            photo = R.drawable.stuban
        ),
        Gallery(
            id = 6,
            name = "Pengambilan Merchandise",
            photo = R.drawable.merch
        ),
        Gallery(
            id = 7,
            name = "COMPFAIR X Dies Natalis Fasilkom",
            photo = R.drawable.compfairxdiesnat
        ),
        Gallery(
            id = 8,
            name = "Hai Desa 6.0",
            photo = R.drawable.haidesa
        ),
        Gallery(
            id = 9,
            name = "Beasiswa For Mahasiswa",
            photo = R.drawable.bfm
        ),
        Gallery(
            id = 10,
            name = "Kajian General X Forum Mahasiswa",
            photo = R.drawable.kajiangeneral
        ),
    )
}