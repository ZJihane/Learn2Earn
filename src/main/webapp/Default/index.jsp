<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>
<body>
  <!-- ======= Header ======= -->
<%@ include file="header.jsp" %>

  <!-- ======= Intro Section ======= -->
  <section id="hero" class="hero d-flex align-items-center">

    <div class="container">
      <div class="row">
        <div class="col-lg-6 d-flex flex-column justify-content-center">
          <h1 data-aos="fade-up">your success starts here!</h1>
          <h2 data-aos="fade-up" data-aos-delay="400">the app that builds bridges between your education and your professional future. Earn money while gaining hands-on experience in your field of study. Enhance your CV, fund your academic journey </h2>
          <div data-aos="fade-up" data-aos-delay="600">
           <div class="d-flex">
    <div class="text-center text-lg-start">
        <a href="signupRecruiter.jsp" class="btn-get-started scrollto d-inline-flex align-items-center justify-content-center align-self-center">
            <span>Post a mission</span>
           <i class="bi bi-chat-dots"></i>

        </a>
    </div>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div class="text-center text-lg-start">
        <a href="${contextPath}/GestionStudents" class="btn-get-started scrollto d-inline-flex align-items-center justify-content-center align-self-center">
            <span>Find a mission</span>
            <i class="bi bi-search"></i>

        </a>
    </div>
</div>

            
          </div>
        </div>
        <div class="col-lg-6 hero-img" data-aos="zoom-out" data-aos-delay="200">
          <img src="../assets/img/career.png" class="img-fluid" alt="">
        </div>
      </div>
    </div>

  </section><!-- End Hero -->

  <main id="main">
    <!-- ======= About Section ======= -->
    <section id="about" class="about">

      <div class="container" data-aos="fade-up">
        <div class="row gx-0">

          <div class="col-lg-6 d-flex flex-column justify-content-center" data-aos="fade-up" data-aos-delay="200">
            <div class="content">
              <h3>Who We Are</h3>
              <h2>an innovative bridge between education and career</h2>
              <p>
               Our platform aims to bridge the gap between students in the midst of their education and companies seeking promising talents. On our platform, companies have the opportunity to propose missions and specific tasks aligned with the student's field of study. Students can apply for these missions, enabling them to gain practical experience directly related to their future professional sector. Upon the successful completion of each mission, students will receive fair compensation. This approach offers a dual advantage: it provides financial support for students' academic journeys while strengthening their CV with a tangible demonstration of their expertise and professional skills
              </p>
            </div>
          </div>

          <div class="col-lg-6 d-flex align-items-center" data-aos="zoom-out" data-aos-delay="200">
            <img src="../assets/img/about.jpg" class="img-fluid" alt="">
          </div>

        </div>
      </div>

    </section><!-- End About Section -->

    <!-- ======= Values Section ======= -->
    <section id="values" class="values">

      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h2>Our Values</h2>
          <p>Here are three key values of Learn2Earn</p>
        </header>

        <div class="row">

          <div class="col-lg-4" data-aos="fade-up" data-aos-delay="200">
            <div class="box">
              <img src="../assets/img/values-1.png" class="img-fluid" alt="">
              <h3>Empowerment through Practical Experience</h3>
              <p>Learn2Earn empowers students by providing them with opportunities to gain practical experience directly related to their field of study. </p>
            </div>
          </div>

          <div class="col-lg-4 mt-4 mt-lg-0" data-aos="fade-up" data-aos-delay="400">
            <div class="box">
              <img src="../assets/img/values-2.png" class="img-fluid" alt="">
              <h3>Financial Support for Academic Journey</h3>
              <p>The platform values the financial well-being of students by offering them fair compensation for successfully completed missions. </p>
            </div>
          </div>

          <div class="col-lg-4 mt-4 mt-lg-0" data-aos="fade-up" data-aos-delay="600">
            <div class="box">
              <img src="../assets/img/values-3.png" class="img-fluid" alt="">
              <h3>Career Development and Skill Enhancement</h3>
              <p>At Learn2Earn,we go beyond academic preparation, ensuring that students are not only academically equipped but also professionally prepared for the challenges in their chosen fields..</p>
            </div>
          </div>

        </div>

      </div>

    </section><!-- End Values Section -->



 

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
<%@ include file="footer.jsp" %>
<%@ include file="links.jsp" %>

</body>

</html>