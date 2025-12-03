# Smart University - Universite Yonetim Sistemi

**BBS 515 - Nesne Yonelimli Programlama**  
**Ogrenci:** Nurcan Denli Bayir | **No:** N25110789

---

## KURULUM / INSTALLATION

### Gereksinimler
- Java JDK 8 veya ustu (Java 11+ onerilir)
- Terminal veya Komut Satiri (CMD/PowerShell)

### Java Kurulu mu Kontrol Et
```bash
java -version
javac -version
```
Eger "command not found" hatasi aliyorsaniz, Java JDK yukleyin:
- Windows: https://adoptium.net/
- Mac: `brew install openjdk`
- Linux: `sudo apt install default-jdk`

---

## DERLEME VE CALISTIRMA / BUILD AND RUN

### Windows Kullanicilari

**Adim 1:** Komut Satirini (CMD) acin
- Windows tusuna basin, "cmd" yazin, Enter

**Adim 2:** Proje klasorune gidin
```cmd
cd C:\Users\KULLANICI_ADI\Downloads\smartuniversity
```

**Adim 3:** Derleyin
```cmd
mkdir out
javac -encoding UTF-8 -d out src\university\model\*.java src\university\util\*.java src\university\service\*.java src\university\Main.java
```

**Adim 4:** Calistirin
```cmd
java -cp out university.Main
```

---

### Mac / Linux Kullanicilari

**Adim 1:** Terminal acin

**Adim 2:** Proje klasorune gidin
```bash
cd ~/Downloads/smartuniversity
```

**Adim 3:** Script izinlerini verin ve calistirin
```bash
chmod +x build.sh run.sh
./build.sh
./run.sh
```

**Alternatif (manuel):**
```bash
mkdir -p out
javac -encoding UTF-8 -d out src/university/model/*.java src/university/util/*.java src/university/service/*.java src/university/Main.java
java -cp out university.Main
```

---

## UNIT TESTLER / UNIT TESTS

Projeyi derledikten sonra unit testleri calistirabilirsiniz:

### Tum Testleri Calistir
```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out university.test.TestRunner
```

### Beklenen Cikti
```
========================================
  Smart University - Unit Tests
========================================

[GradeUtils Tests]
  [PASS] toGradePoint(95) == 4.0
  [PASS] toGradePoint(90) == 4.0
  ...

[Student Tests]
  [PASS] Create valid student
  ...

[GPA Calculation Tests]
  [PASS] GPA calculation (86% + 72%) = 3.27
  ...

========================================
  TEST RESULTS
========================================
  Passed: 38
  Failed: 0
  Total:  38
========================================
```

### Test Kapsamı
- **GradeUtilsTest:** Not puan donusum ve sınır degerleri
- **StudentTest:** Ogrenci olusturma ve validasyon
- **CourseTest:** Ders olusturma ve validasyon
- **EnrollmentTest:** Kayit olusturma ve notlandirma
- **UniversityTest:** CRUD islemleri ve hata durumlari
- **GPATest:** Kredi agirlikli GPA hesaplama

---

## KULLANIM KILAVUZU / USER GUIDE

Program basladiginda su mesaji goreceksiniz:
```
Smart University (no inheritance). Type 'help' for commands.
>
```

### Temel Komutlar

| Komut | Aciklama | Ornek |
|-------|----------|-------|
| `help` | Tum komutlari listeler | `help` |
| `exit` | Programdan cikar | `exit` |

### Ogrenci Islemleri

| Komut | Aciklama | Ornek |
|-------|----------|-------|
| `add-student` | Yeni ogrenci ekler | `add-student 1001 "Ali Yilmaz" "Bilgisayar Muh."` |
| `list-students` | Tum ogrencileri listeler | `list-students` |
| `gpa` | Ogrenci GPA hesaplar | `gpa 1001` |

### Ders Islemleri

| Komut | Aciklama | Ornek |
|-------|----------|-------|
| `add-course` | Yeni ders ekler | `add-course CS101 "Programlama" 4 "Dr. Mehmet"` |
| `list-courses` | Tum dersleri listeler | `list-courses` |

### Kayit ve Notlandirma

| Komut | Aciklama | Ornek |
|-------|----------|-------|
| `enroll` | Ogrenciyi derse kaydeder | `enroll 1001 CS101` |
| `grade` | Ogrenciye not verir (0-100) | `grade 1001 CS101 85` |
| `list-enrollments` | Tum kayitlari listeler | `list-enrollments` |

### Veri Kaydetme/Yukleme

| Komut | Aciklama | Ornek |
|-------|----------|-------|
| `save` | Verileri CSV dosyalarina kaydeder | `save` |
| `load` | CSV dosyalarindan verileri yukler | `load` |

---

## ADIM ADIM ORNEK SENARYO

Asagidaki adimlari sirayla yazarak sistemi test edebilirsiniz:

```
> add-student 1001 "Ahmet Yilmaz" "Bilgisayar Muhendisligi"
Added student.

> add-student 1002 "Ayse Demir" "Yazilim Muhendisligi"
Added student.

> add-course CS101 "Nesne Yonelimli Programlama" 4 "Prof. Dr. Mehmet Oz"
Added course.

> add-course MATH201 "Lineer Cebir" 3 "Doc. Dr. Fatma Kaya"
Added course.

> list-students
1001 | Ahmet Yilmaz | Bilgisayar Muhendisligi
1002 | Ayse Demir | Yazilim Muhendisligi

> list-courses
CS101 | Nesne Yonelimli Programlama | 4cr | Prof. Dr. Mehmet Oz
MATH201 | Lineer Cebir | 3cr | Doc. Dr. Fatma Kaya

> enroll 1001 CS101
Enrolled.

> enroll 1001 MATH201
Enrolled.

> grade 1001 CS101 86
Grade recorded.

> grade 1001 MATH201 72
Grade recorded.

> list-enrollments
1001,CS101,86.0
1001,MATH201,72.0

> gpa 1001
GPA for 1001: 3.27

> save
Saved to ./data

> exit
Bye.
```

---

## NOT SISTEMI / GRADING SCALE

| Yuzde Aralik | GPA Puani |
|--------------|-----------|
| 90 - 100 | 4.0 |
| 85 - 89 | 3.7 |
| 80 - 84 | 3.3 |
| 75 - 79 | 3.0 |
| 70 - 74 | 2.7 |
| 65 - 69 | 2.3 |
| 60 - 64 | 2.0 |
| 55 - 59 | 1.7 |
| 50 - 54 | 1.0 |
| 0 - 49 | 0.0 (Basarisiz) |

**GPA Hesaplama Formulu:**
```
GPA = Toplam(Kredi x GPA_Puani) / Toplam(Kredi)
```

**Ornek:**
- CS101: 4 kredi, 86% = 3.7 puan -> 4 x 3.7 = 14.8
- MATH201: 3 kredi, 72% = 2.7 puan -> 3 x 2.7 = 8.1
- Toplam: (14.8 + 8.1) / (4 + 3) = 22.9 / 7 = **3.27**

---

## CSV DOSYA FORMATLARI

Veriler `./data` klasorune kaydedilir:

**students.csv:**
```csv
id,name,major
1001,"Ahmet Yilmaz","Bilgisayar Muhendisligi"
```

**courses.csv:**
```csv
code,title,credits,instructor
"CS101","Nesne Yonelimli Programlama",4,"Prof. Dr. Mehmet Oz"
```

**enrollments.csv:**
```csv
studentId,courseCode,gradePercent
1001,CS101,86.0
```

---

## PROJE YAPISI / PROJECT STRUCTURE

```
smartuniversity/
├── src/
│   └── university/
│       ├── model/
│       │   ├── Student.java      # Ogrenci sinifi
│       │   ├── Course.java       # Ders sinifi
│       │   └── Enrollment.java   # Kayit sinifi
│       ├── service/
│       │   └── University.java   # Is mantigi
│       ├── util/
│       │   ├── GradeUtils.java   # Not hesaplama
│       │   ├── CSVExporter.java  # CSV yazma
│       │   └── CSVImporter.java  # CSV okuma
│       └── Main.java             # Ana program (CLI)
├── data/                         # CSV dosyalari (save sonrasi olusur)
├── out/                          # Derlenmis .class dosyalari
├── build.sh                      # Mac/Linux derleme scripti
├── run.sh                        # Mac/Linux calistirma scripti
└── README.md                     # Bu dosya
```

---

## TASARIM PRENSIPLERI / DESIGN PRINCIPLES

Bu proje **kalitim kullanmadan (No Inheritance)** gelistirilmistir:

- **Final Classes:** Tum siniflar `final` olarak tanimlanmistir
- **No extends:** Hicbir sinif baska bir siniftan turetilmemistir
- **No interface:** Interface kullanilmamistir
- **Composition:** Kalitim yerine bilesim (composition) kullanilmistir
- **Encapsulation:** Tum alanlar `private`, getter/setter ile erisim

---

## SORUN GIDERME / TROUBLESHOOTING

### "javac: command not found" Hatasi
Java JDK kurulu degil. Yukaridaki "Gereksinimler" bolumune bakin.

### "could not find or load main class" Hatasi
`out` klasorunde oldugunuzdan emin olun veya dogru yolu kullanin:
```bash
java -cp out university.Main
```

### Turkce Karakter Sorunu
UTF-8 encoding kullanin:
```bash
java -cp out -Dfile.encoding=UTF-8 university.Main
```

---

## LISANS / LICENSE

Bu proje egitim amaclidir.  
(c) 2025 - BBS 515 Nesne Yonelimli Programlama
