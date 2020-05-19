import UpdateDialog from "./UpdateDialog";
const React = require('react');

export default class Employee extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.employee);
    }

    render() {
        const entity = this.props.employee.entity
        return (
            <tr>
                <td>{entity.firstName}</td>
                <td>{entity.lastName}</td>
                <td>{entity.description}</td>
                <td>{entity.manager && entity.manager.name}</td>
                <td>
                    <UpdateDialog employee={this.props.employee}
                                  attributes={this.props.attributes}
                                  onUpdate={this.props.onUpdate}
                                  loggedInManager={this.props.loggedInManager}/>
                </td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        )
    }
}