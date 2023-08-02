function ProfileCard({title, handle, alt, img, description}) {
    //alternative
        //const title = props.title;
        //const handle = props.handle;
        //const img = props.img;
        //const alt = props.alt;
    //or const {title, handle} = props;
    //or instead of props as arguments, {title, handle}
    return (<div className="card">
        <div className="card-image">
            <figure className="image is-1by1">
                <img src={img} alt={alt}/> 
            </figure>
        </div>

        <div className="card-content">
            <div className="media-content">
                <p className="title is-4">{title}</p>
                <p className="subtitle is-6">{handle}</p>
            </div>
            <div className="content">
                {description}
            </div>
        </div>

    </div>);
    
}

export default ProfileCard;