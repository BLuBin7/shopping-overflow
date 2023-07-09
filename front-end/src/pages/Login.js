import React from "react";

class Form extends React.Component {
  // key value
  state = {
    firstName: "",
    lastName: "",
  };

  // nhập họ
  handleChangeFirstName = (event) => {
    //  dùng {} , do đây là 1 khối (block)
    this.setState({
      firstName: event.target.value,
    });
  };

  // nhập tên 
  handleChangeLastName = (event) => {
    //  dùng {} , do đây là 1 khối (block)
    this.setState({
      lastName: event.target.value,
    });
  };

//   dùng arrow fuction
  handleSubmit = (event) => {
    // in ra thông báo dùng alert
    alert(`${event.target.value} ${this.state.lastName}`);

    // trang sẽ ko tải lại 
    event.preventDefault();
  }

  render() {
    return (
      <>
        <form>
          <label htmlFor="fname">First name:</label>
          {/* html sang react phải có "/" ở cuối  */}
          <br />
          <input
            type="text"
            value={this.state.firstName}
            onChange={(event) => this.handleChangeFirstName(event)}
          />
          <br />
          <label htmlFor="lname">Last name:</label>
          <br />
          <input
            type="text"
            value={this.state.lastName}
            onChange={(event) => this.handleChangeLastName(event)}
          />
          <br />
          <input 
           type="submit" 
           onClick={(event) => this.handleSubmit(event)}
          />
        </form>
      </>
    );
  }
}
export default Form;