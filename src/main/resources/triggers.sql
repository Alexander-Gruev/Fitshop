-- products_trigger
IF EXISTS (
        SELECT * FROM sys.triggers
        WHERE [name] = 'products_trigger'
    )
    DROP TRIGGER [19118051].[products_trigger];

CREATE TRIGGER [19118051].[products_trigger]
    ON [19118051].[products]
    AFTER INSERT, UPDATE
    AS
BEGIN
    -- Check if this is an INSERT operation
    IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'INSERT', 'products')
        END
        -- Check if this is an UPDATE operation
    ELSE IF EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'UPDATE', 'products')
        END
END;


-- -- orders_trigger
IF EXISTS (
            SELECT * FROM sys.triggers
            WHERE [name] = 'orders_trigger'
        )
        DROP TRIGGER [19118051].[orders_trigger];

CREATE TRIGGER [19118051].[orders_trigger]
    ON [19118051].[orders]
    AFTER INSERT, UPDATE
    AS
BEGIN
    -- Check if this is an INSERT operation
    IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'INSERT', 'orders')
        END
        -- Check if this is an UPDATE operation
    ELSE IF EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'UPDATE', 'orders')
        END
END;


-- -- users_trigger
IF EXISTS (
            SELECT * FROM sys.triggers
            WHERE [name] = 'users_trigger'
        )
        DROP TRIGGER [19118051].[users_trigger];

CREATE TRIGGER [19118051].[users_trigger]
    ON [19118051].[users]
    AFTER INSERT, UPDATE
    AS
BEGIN
    -- Check if this is an INSERT operation
    IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'INSERT', 'users')
        END
        -- Check if this is an UPDATE operation
    ELSE IF EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'UPDATE', 'users')
        END
END;


-- -- roles_trigger
IF EXISTS (
            SELECT * FROM sys.triggers
            WHERE [name] = 'roles_trigger'
        )
        DROP TRIGGER [19118051].[roles_trigger];

CREATE TRIGGER [19118051].[roles_trigger]
    ON [19118051].[roles]
    AFTER INSERT, UPDATE
    AS
BEGIN
    -- Check if this is an INSERT operation
    IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'INSERT', 'roles')
        END
        -- Check if this is an UPDATE operation
    ELSE IF EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'UPDATE', 'roles')
        END
END;


-- -- users_roles_trigger
IF EXISTS (
            SELECT * FROM sys.triggers
            WHERE [name] = 'users_roles_trigger'
        )
        DROP TRIGGER [19118051].[users_roles_trigger];

CREATE TRIGGER [19118051].[users_roles_trigger]
    ON [19118051].[users_roles]
    AFTER INSERT, UPDATE
    AS
BEGIN
    -- Check if this is an INSERT operation
    IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'INSERT', 'users_roles')
        END
        -- Check if this is an UPDATE operation
    ELSE IF EXISTS(SELECT * FROM inserted) AND EXISTS(SELECT * FROM deleted)
        BEGIN
            INSERT INTO [19118051].[log_19118051] ("operation_date_time", "operation_type", "table_name")
            VALUES (GETDATE(), 'UPDATE', 'users_roles')
        END
END;



