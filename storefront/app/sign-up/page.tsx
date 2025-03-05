import React from 'react';
import '@/styles/style.css'
import '@/styles/index.css'
import '@fortawesome/fontawesome-free/css/all.min.css';


const SigninPage: React.FC = () => {
    return (
        <div className="signin-page">
            <div className="signin-container d-flex align-items-center min-vh-100">
                <div className="row">
                    <div className="col-md-6 my-3">
                        <div className="signin-login text-center">
                            <form>
                                <div className="form_header">
                                    <h1 className="fw-bold text-primary mb-4">Sign In</h1>
                                </div>

                                <div className="signin-input-field rounded-pill w-75 mx-auto">
                                    <span className="position-absolute top-50 end-0 translate-middle-y me-3 cursor-pointer">
                                        <i className="fa fa-user"></i>
                                    </span>
                                    <input type="text" placeholder="Username" className="form-control" />
                                </div>

                                <div className="signin-input-field rounded-pill w-75 mx-auto position-relative">
                                    <span className="position-absolute top-50 end-0 translate-middle-y me-3 cursor-pointer">
                                        <i className="fa fa-lock"></i>
                                    </span>
                                    <input type="password" placeholder="Password" className="form-control" />
                                    <span className="position-absolute top-50 end-0 translate-middle-y me-3 cursor-pointer">
                                        <i className="fa fa-eye"></i>
                                    </span>
                                </div>

                                <div className="signin-submit_btn my-4">
                                    <button type="submit" className="btn btn-lg btn-outline-primary rounded-pill px-5 fw-bold">
                                        Sign in
                                    </button>
                                </div>

                                <p>Or Sign up with social platform</p>
                                <div className="signin-social-icon d-flex justify-content-center">
                                    <a href="#" className="social-icon facebook">
                                        <i className="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#" className="social-icon google">
                                        <i className="fab fa-google"></i>
                                    </a>
                                    <a href="#" className="social-icon gmail">
                                        <i className="fas fa-envelope"></i>
                                    </a>
                                    <a href="#" className="social-icon sms">
                                        <i className="fas fa-sms"></i>
                                    </a>
                                </div>

                                <br />
                                <a href="/sign-in" className="btn btn-lg btn-outline-primary rounded-pill px-5 fw-bold">
                                    Sign in
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SigninPage;
