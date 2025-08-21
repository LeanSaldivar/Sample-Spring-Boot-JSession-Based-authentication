function LoginHeader() {
    return (
        <div className="header-container">
            <h1 className="header-text">
                Welcome Back .!
            </h1>

            <div className="subtext-container">
                <p className="subtext">
                    Skip the lag ?
                </p>
                <svg
                    className="dashed-line"
                    viewBox="0 0 100 2"
                    preserveAspectRatio="none"
                >
                    <line x1="0" y1="1" x2="100" y2="1"/>
                </svg>
            </div>
        </div>
    )
}

export default LoginHeader